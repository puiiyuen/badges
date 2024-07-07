import { Bool, OpenAPIRoute } from 'chanfana'
import { z } from 'zod'

import { submissionsBadge } from '../../services/submissions'
import {
  difficultiesParam,
  shieldExtraConfigsParams,
  successfulResponse,
  usernameParam,
} from '../common'

export class Submissions extends OpenAPIRoute {
  schema = {
    tags: ['LeetCode'],
    request: {
      params: usernameParam,
      query: z
        .object({})
        .merge(difficultiesParam)
        .merge(
          z.object({
            accepted: Bool({
              default: false,
              required: false,
            }),
          })
        )
        .merge(shieldExtraConfigsParams),
    },
    responses: {
      200: successfulResponse,
    },
  }

  async handle() {
    const data = await this.getValidatedData<typeof this.schema>()
    const { username } = data.params
    const { difficulty, accepted, ...shieldExtraConfigs } = data.query
    const shield = await submissionsBadge(
      username,
      difficulty,
      accepted,
      shieldExtraConfigs
    )
    return new Response(shield, {
      headers: {
        'content-type': 'image/svg+xml',
      },
    })
  }
}
