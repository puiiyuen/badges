import { OpenAPIRoute } from 'chanfana'

import { rankingBadge } from '../../services/ranking'
import {
  shieldExtraConfigsParams,
  successfulResponse,
  usernameParam,
} from '../common'

export class Ranking extends OpenAPIRoute {
  schema = {
    tags: ['LeetCode'],
    request: {
      params: usernameParam,
      query: shieldExtraConfigsParams,
    },
    responses: {
      200: successfulResponse,
    },
  }

  async handle() {
    const data = await this.getValidatedData<typeof this.schema>()
    const { username } = data.params
    const { ...shieldExtraConfigs } = data.query
    const shield = await rankingBadge(username, shieldExtraConfigs)
    return new Response(shield, {
      headers: {
        'content-type': 'image/svg+xml',
      },
    })
  }
}
