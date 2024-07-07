import { Bool, Enumeration, OpenAPIRoute, Str } from 'chanfana'
import { z } from 'zod'

import { difficulties } from '../../adapters/leetcode'
import { submissionsBadge } from '../../services/submissions'

export class Submissions extends OpenAPIRoute {
  schema = {
    tags: ['LeetCode v1'],
    request: {
      query: z.object({
        difficulty: Enumeration({
          values: difficulties,
          default: 'All',
        }),
        accepted: Bool({
          default: false,
          required: false,
        }),
      }),
      params: z.object({
        username: Str({
          required: true,
        }),
      }),
    },
    responses: {
      200: {
        description: 'Successful Response',
        content: {
          'img/svg+xml': {
            schema: Str(),
          },
        },
      },
    },
  }

  async handle() {
    const data = await this.getValidatedData<typeof this.schema>()
    const { username } = data.params
    const { difficulty, accepted } = data.query
    const shield = await submissionsBadge(username, difficulty, accepted)
    return new Response(shield, {
      headers: {
        'content-type': 'image/svg+xml',
      },
    })
  }
}
